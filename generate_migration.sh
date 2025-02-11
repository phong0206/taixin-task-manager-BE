#!/bin/bash

MIGRATION_DIR="src/main/resources/db/migration"

MIGRATION_LATEST_CHANGES_FILE="src/main/resources/db/migration/migration_latest_changes.sql"

# Tạo thư mục nếu chưa tồn tại
mkdir -p "$MIGRATION_DIR"

# Tìm file SQL mới nhất trong thư mục migration (bỏ qua latest_changes.sql)
MIGRATION_LATEST_SQL=$(ls -t "$MIGRATION_DIR"/V*.sql 2>/dev/null | head -n 1)

# Nếu không có file nào, bắt đầu từ V1
if [ -z "$MIGRATION_LATEST_SQL" ]; then
    NEXT_VERSION=1
else
    # Lấy số phiên bản từ tên file (tìm Vx trong Vx__...)
    LATEST_VERSION=$(basename "$MIGRATION_LATEST_SQL" | grep -Eo '^V[0-9]+' | sed 's/V//')

    # Kiểm tra nếu không tìm thấy số phiên bản
    if ! [[ "$LATEST_VERSION" =~ ^[0-9]+$ ]]; then
        echo "❌ Không tìm thấy số phiên bản hợp lệ từ file: $MIGRATION_LATEST_SQL"
        exit 1
    fi

    # Tăng số phiên bản lên 1
    NEXT_VERSION=$((LATEST_VERSION + 1))
fi

# Lấy timestamp hiện tại (định dạng YYYYMMDDHHMMSS)
TIMESTAMP=$(date +"%Y%m%d%H%M%S")

# Lấy tên file từ tham số truyền vào (nếu không có, chỉ dùng timestamp)
if [ -z "$1" ]; then
    MIGRATION_FILENAME="V${NEXT_VERSION}__${TIMESTAMP}.sql"
else
    MIGRATION_FILENAME="V${NEXT_VERSION}__${TIMESTAMP}__$1.sql"
fi

# Đường dẫn file SQL migration mới
NEW_MIGRATION_FILE="$MIGRATION_DIR/$MIGRATION_FILENAME"

# Kiểm tra xem file Hibernate SQL (`latest_changes.sql`) có tồn tại không
if [ ! -f "$MIGRATION_LATEST_CHANGES_FILE" ]; then
  echo "⚠️ Not found file Hibernate schema migration_latest_changes.sql. Run app before creating file SQL from Hibernate!"
  exit 1
fi

# Di chuyển nội dung từ latest_changes.sql sang file migration mới (nếu tồn tại)
if [ -f "$MIGRATION_LATEST_CHANGES_FILE" ]; then
    mv "$MIGRATION_LATEST_CHANGES_FILE" "$NEW_MIGRATION_FILE"
    echo "✅ SQL migration file created: $NEW_MIGRATION_FILE"
else
    echo "⚠️ File $MIGRATION_LATEST_CHANGES_FILE does not exists, do not move!"
fi
