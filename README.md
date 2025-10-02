# 🏥 Clínica - Sistema de Gerenciamento

Este repositório contém uma aplicação de gerenciamento de clínica com integração a banco de dados PostgreSQL.  

---

## 📥 Como executar a aplicação

### 1. Baixando os arquivos necessários
1. Acesse o link do repositório no GitHub.  
2. Clique no botão verde **Code**.  
3. Selecione a opção **Download ZIP**.  
4. Uma pasta compactada será baixada.  
5. Descompacte a pasta.  

➡️ Dentro dela, você encontrará:
- Um arquivo **dbclinica.txt** (script do banco de dados).  
- Uma pasta chamada **Clinica** (contendo os arquivos da aplicação).  

---

### 2. Configurando o Banco de Dados
1. Abra o **arquivo `dbclinica.txt`** e copie todo o conteúdo.  
2. No **pgAdmin**, crie um novo banco:  
   - Clique em **Create Database**.  
   - Nomeie como **dbclinica**.  
3. Cole o script copiado e execute-o para criar as tabelas.  

---

### 3. Configurando a aplicação
1. Abra o **NetBeans**.  
2. Vá em **File → Open Project**.  
3. Selecione a pasta **Clinica** (baixada do repositório).  
4. Abra a classe **Clinica.java**.  
5. Localize a linha 14:  

   ```java
   String senha = "";

➡️ Insira entre aspas a senha do seu PostgreSQL.

6. Clique em Run para iniciar a aplicação.

### ▶️ Executando
Após rodar, o terminal exibirá o menu de opções da aplicação.
Basta digitar o número correspondente à ação desejada.

### ✅ Pronto!
Agora você já pode utilizar todas as funcionalidades do sistema. 🚀
