# 🛰️ Sistema de Monitoramento Agrícola com Drones

## 📝 Visão Geral do Projeto

O Sistema de Monitoramento Agrícola é desenvolvido para uma cooperativa rural com o objetivo de gerenciar o agendamento de **missões de voo** e a coleta de dados ambientais (imagens, temperatura, umidade) usando drones.

O projeto foca em aplicar rigorosos princípios de **Engenharia de Software (UML, Orientação a Objetos)** e **Segurança**, garantindo a integridade dos dados e a segurança operacional dos drones.

---

## ✅ Sprint 1: Modelagem, Segurança e Integração (Concluído)

A primeira fase do projeto estabeleceu a arquitetura fundamental e implementou os requisitos críticos de segurança.

### 1. Entidades de Domínio e Classes Principais

Todas as entidades foram mapeadas com foco em **Encapsulamento** e **Separação de Responsabilidades**:

* `AreaAgricola`, `Usuario`, `Drone`, `DadoColetado`.
* `MissaoVoo`: Centraliza as regras de agendamento e segurança.
* `MissaoDAO`: Objeto de Acesso a Dados, responsável apenas pela persistência segura.

### 2. Diagramas de Análise e Projeto (UML Formal)

Os diagramas foram formalmente detalhados para cumprir os requisitos de documentação:

#### A. Diagrama de Classes
* **Foco:** Estrutura estática com notação formal (visibilidade, tipos de dados e associações).



#### B. Diagrama de Sequência (Fluxo Seguro)
* **Foco:** Detalhar a ordem das chamadas do processo de agendamento, integrando segurança e tratamento de erros.



### 3. Integração e Segurança (Etapa Crítica)

O sistema garante a segurança da persistência de dados e da operação do hardware:

| Requisito de Segurança | Classe(s) Implementada(s) | Mecanismo de Proteção |
| :--- | :--- | :--- |
| **Checklist de Pré-Voo** | `Drone.java` | Lógica `checarPreVoo()` (Bateria Mínima, Sensores OK) chamada antes de agendar. |
| **Armazenamento de Senha** | `Usuario.java` | Uso de `senhaHash` e método seguro, prevenindo armazenamento de senha em texto puro no BD. |
| **Anti-Injeção de SQL** | `MissaoDAO.java` | Utilização de **`PreparedStatement`** no comando `INSERT`, que trata os dados como valores (e não código SQL). |
| **Não Sobreposição de Missões**| `MissaoVoo.java` | Lógica `validarNaoSobreposta()` checa conflitos de horário com o BD. |

---

## 🏗️ Sprint 2: Arquitetura e Abstração (Próximos Passos)

O próximo ciclo se concentra no refinamento da arquitetura utilizando abstrações de nível superior.

### ETAPA #5: Diagrama de Projeto (com abstrações e interfaces)

O foco será refatorar o código para melhorar a escalabilidade e manutenção:

* **Abstração e Herança:** A classe **`Usuario`** será transformada em **abstrata**, criando as subclasses **`Administrador`** e **`OperadorDrone`** para lidar com permissões distintas.
* **Interfaces:** Criação da interface **`ChecklistVoo`** para padronizar o contrato de verificação de aptidão de voo, garantindo que o método `checarPreVoo()` siga um contrato formal.

---

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java
* **Modelagem:** UML 2.0 (PlantUML/Mermaid)
* **Persistência:** SQL (JDBC, DDL)
* **Princípios:** Encapsulamento, Separação de Responsabilidades (DAO), Prevenção de Injeção de SQL.
