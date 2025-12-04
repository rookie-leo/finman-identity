# Pessoa microservice

Microserviço responsavel pelo gerenciamento de conta do usuario.<br>

## Endpoins

O endpoint cadastro, será responsavel pela criação da conta de usuario, e captura todos os dados necessarios para a
criação da conta.<br>
O endpoint login, será responsavel por verificar se o usuario está cadastrado na base de dados e irá devolver como
resposta um token jwt com dados necessarios para o usuario navegar nos endpoints de atualização e deleção de conta,
entre outros futuros.<br>
O endpoint atualização, será responsavel por atualizar os dados cadastrais do usuario, e só permitirá tal ação, caso o
usuario esteja logado. Como resposta, irá retornar apenas o status 200.<br>
O endpoint Deletar conta, será responsavel por deletar a conta do usuario, porém apenas será alterado o status da conta
para deletado, e após um periodo a ser determinado (6 meses por enquanto), os dados serão deletados permanentemente.

## Desenho tecnico

![pessoa-ms](https://github.com/rookie-leo/img/blob/master/pessoa-ms-draw.png)

## Regras de Negócio

* Cadastro<br>
    * Toda pessoa pode se cadastrar usando seu nome, documento, email valido e senha.<br>
    * Uma pessoa pode ter apenas um cadastro por documento.<br>
    * O email deve ser unico na base de cadastros.
* Login
    * Um usuario cadastrado poderá realizar seu login usando email e senha.<br>
* Atualização
    * O usuario poderá atualizar seu nome. <br>
    * O usuario poderá atualizar seu email.<br>
    * O usuario poderá atualizar sua senha.<br>
* Deletar conta
    * O usuario poderá deletar sua conta de forma temporaria.<br>
    * Caso o usuario queira voltar atrás com a deleção de sua conta, o mesmo poderá fazer a recuperação da conta
      deletada no periodo de 3 meses.
