run app & create file sql change: mvn spring-boot:run -X
generate file migration: ./generate_migration.sh init_db
run migration: mvn flyway:migrate
