## Pessoa microservice

Este repositório será para a pratica de arquitetura de microserviços, onde será implementado um CRUD da entidade pessoa.<br>

### Endpoins
O endpoint cadastro, será responsavel pela criação da conta de usuario, e captura todos os dados necessarios para a criação da conta.<br>
O endpoint login, será responsavel por verificar se o usuario está cadastrado na base de dados e irá devolver como resposta um token jwt com dados necessarios para o usuario navegar nos endpoints de atualização e deleção de conta, entre outros futuros.<br>
O endpoint atualização, será responsavel por atualizar os dados cadastrais do usuario, e só permitirá tal ação, caso o usuario esteja logado. Como resposta, irá retornar apenas o status 200.<br>
O endpoint Deletar conta, será responsavel por deletar a conta do usuario, porém apenas será alterado o status da conta para deletado, e após um periodo a ser determinado (6 meses por enquanto), os dados serão deletados permanentemente.

### Desenho tecnico
![pessoa-ms](https://github.com/rookie-leo/img/blob/master/pessoa-ms-draw.png)

### Regras de Negócio

