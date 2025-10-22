# rgr-transferencias

Sistema de agendamento de transferências financeiras com cálculo automático de taxas conforme a data da operação.

---

## Arquitetura

O projeto foi desenvolvido seguindo o padrão MVC (Model–View–Controller), aplicando boas práticas de separação de camadas:

Controller: Camada de entrada da aplicação, responsável por expor endpoints REST.

Service: Contém as regras de negócio (validações e cálculo da taxa).

Repository: Camada de persistência, implementada com Spring Data JPA.

Model: Representa a entidade TransferenciaService.

DTO: Transfere dados entre a API e o domínio (Data Transfer Object).

## Tecnologias Utilizadas

Linguagem	Java 17 (Suporte ao java 11)
Framework principal	Spring Boot 3.x
Persistência	Spring Data JPA
Banco de dados	H2 Database (em memória)
Gerenciador de dependências	Maven
Versionamento	Git + GitHub
IDE utilizada	IntelliJ IDEA

## Estrutura de Pastas

rgr-transferencias/
└── backend/
    └── transferencias/
        └── src/
            └── main/
                ├── java/com/rgr/transferencias/
                │   ├── controller/ → Controladores REST
                │   ├── dto/ → Objetos de Transferência de Dados
                │   ├── model/ → Entidades do banco
                │   ├── repository/ → Repositórios JPA
                │   └── service/ → Lógica de negócio
                └── resources/
                    ├── static/
                    ├── templates/
                    └── application.properties
					
## Funcionalidades Implementadas

✅ Agendar transferência financeira
✅ Listar todas as transferências cadastradas
✅ Buscar transferência por ID
✅ Excluir (deletar) transferência
✅ Cálculo automático de taxa conforme o prazo entre agendamento e execução

## Execução do projeto

### Back-end
Certifique-se de ter o Java 17+ e o Maven instalados.

Vá até a pasta:

cd backend/transferencias

Execute o projeto:

mvn spring-boot:run

O servidor subirá por padrão em:
http://localhost:8080/api/transferencias

Endpoints Disponíveis
Método	Endpoint	Descrição
GET	/api/transferencias	Lista todas as transferências
POST	/api/transferencias	Agenda uma nova transferência
GET	/api/transferencias/{id}	Busca transferência por ID
DELETE	/api/transferencias/{id}	Remove uma transferência
