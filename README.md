# 🛰️ Sistema de Monitoramento Agrícola com Drones (Projeto Final)

## 📌 Visão Geral e Foco Arquitetural

Este projeto é um sistema de software para gestão de **Missões de Voo** de drones agrícolas. O desenvolvimento segue rigorosamente os princípios de **Engenharia de Software (UML)** e **Orientação a Objetos (OO)**, com ênfase na **segurança operacional** e na **integração robusta** com o banco de dados.

### 🎯 Requisitos de Segurança Implementados

| Requisito | Classe(s) Envolvida(s) | Mecanismo de Proteção |
| :--- | :--- | :--- |
| **Prevenção Injeção de SQL** | `MissaoDAO.java` | Uso de **`PreparedStatement`** (evita interpretação de dados como código SQL). |
| **Armazenamento Seguro Senha** | `Usuario.java` | Uso de **Hash Simulado** (nunca armazena senha em texto puro). |
| **Checklist de Pré-Voo** | `Drone.java` / `ChecklistVoo.java` | Lógica **`checarAptidao()`** (validação de bateria/sensores) chamada antes de qualquer agendamento. |
| **Controle de Acesso** | `Usuario.java` (Abstrata) | **Herança e Polimorfismo** definem permissões para `Administrador` e `OperadorDrone`. |
| **Validação de Sobreposição**| `MissaoVoo.java` | Regra de Negócio que impede duas missões no mesmo drone no mesmo horário. |

---

## 🏗️ Modelagem Estrutural e Arquitetural (ETAPAS #2 e #5)

A arquitetura do projeto evoluiu para incorporar abstrações e interfaces para maior escalabilidade.

### 1. Diagrama de Classes de Projeto

O diagrama final inclui a refatoração com Abstração (`Usuario`) e Interfaces (`ChecklistVoo`), garantindo a notação formal UML.



### 2. Conceitos de Orientação a Objetos (OO)

| Conceito | Classes/Relação | Explicação |
| :--- | :--- | :--- |
| **Encapsulamento** | Todas as classes | Atributos privados (`private`) acessados via `getters` e `setters`. |
| **Abstração & Herança** | `Usuario` (abstrata) -> `Administrador`, `OperadorDrone` | Permite o tratamento genérico, mas com implementação específica para cada perfil. |
| **Interface** | `ChecklistVoo` implementada por `Drone` | Define um contrato formal (`checarAptidao()`) para qualquer componente que precise de validação de segurança. |
| **Associação** | `MissaoVoo` referencia `Drone`, `AreaAgricola` e `Usuario` | Relacionamentos que permitem o agendamento. |
| **Composição** | `MissaoVoo` e `DadoColetado` | Os `DadosColetados` são gerados e pertencem estritamente a uma `MissaoVoo`. |

---

## 📈 Modelagem Comportamental (ETAPAS #3, #6 e #7)

Os diagramas comportamentais detalham o fluxo dinâmico e o ciclo de vida das entidades.

### 1. Diagrama de Sequência e Colaboração (Agendamento Seguro)

O diagrama mostra a ordem das mensagens (Sequência) e os links entre os objetos (Colaboração), incluindo o tratamento de exceções.



### 2. Diagrama de Estados da Missão de Voo

O Diagrama de Estados define as transições válidas para a `MissaoVoo`, integrando o fluxo de segurança à sua vida útil.

).]

---

## 💾 Integração e Persistência (ETAPA #4)

### 1. Modelo Relacional (SQL DDL)

O Diagrama Entidade-Relacionamento (DER) mapeia as classes para as tabelas do banco de dados, garantindo a integridade referencial:

* **Tabelas:** `TB_USUARIO`, `TB_AREA`, `TB_DRONE`, `TB_MISSAO`, `TB_DADOS_COLETADOS`.
* **Chaves:** Definição de `PRIMARY KEY` e `FOREIGN KEY` (ex: `fk_drone` em `TB_MISSAO`).

### 2. Demonstração no Código Java

A demonstração em `Main.java` executa três cenários de teste, provando a funcionalidade e a segurança do sistema:

1.  ✅ **Sucesso:** Agendamento aprovado e geração de `DadoColetado` (Composição).
2.  ❌ **Rejeição por Segurança:** Falha no `Drone.checarAptidao()` (bateria baixa).
3.  ❌ **Rejeição por Negócio:** Falha na `MissaoVoo.validarNaoSobreposta()` (conflito de horário).

```java
// Exemplo de Invocação Polimórfica (Demonstração de Abstração/Herança)
Usuario operador = new OperadorDrone(1, "Ana Operadora", "ana@drone.com", "senha123"); 
// A permissão é verificada dinamicamente:
if (operador.temPermissaoParaAgendar()) { /* ... */ }
