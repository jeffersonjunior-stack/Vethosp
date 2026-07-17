# Sistema VetHosp

**Projeto acadêmico** - Gerenciamento de setores e boxes de uma Clínica Veterinária.

Desenvolvido em Java 17 + Maven + JDBC + MySQL.

## Como executar

1. Crie o banco de dados:
   ```bash
   mysql -u root -p < database/schema.sql
---

### ⚠️ Configuração Importante do Banco de Dados

Para que o projeto execute corretamente na sua máquina, atente-se às credenciais de conexão do banco de dados configuradas na classe `GerenciadorDeConexao`:

* **URL:** `jdbc:mysql://localhost:3306/vethosp_db`
* **Usuário:** `root`
* **Senha:** *Vazia* (`""`)

> **Nota:** Caso o seu MySQL local possua uma senha configurada para o usuário `root`, lembre-se de alterar o parâmetro de senha na linha abaixo, localizada no arquivo `GerenciadorDeConexao.java`:
> ```java
> return DriverManager.getConnection(URL_PADRAO, "root", "SUA_SENHA_AQUI");
> ```

---
