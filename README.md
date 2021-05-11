# App Widget e Custom View
Adaptado de [Android Advanced Training](https://developer.android.com/courses/advanced-training/overview)

## Widget

Widgets de app são visualizações de aplicativos em miniatura que podem ser incorporadas em outros aplicativos (como a tela inicial) e receber atualizações periódicas. Essas visualizações são chamadas de "widgets" na interface do usuário, e é possível publicá-las com um provedor de widgets de app.
[Documentação](https://developer.android.com/guide/topics/appwidgets?hl=pt-br)

Componentes de um Widget  :
- Provider-info XML file
	-  define os metadados  
- arquivo de Layout XML para UI  
- AppWidgetProvider 
	- codigo Java
- Activity de configuração (opcional)

## Custom View

Uma Custom View pode ser definida como um componente que, diferentemente de uma view “tradicional”, terá o comportamento e a aparência esperada pelo desenvolvedor.

A princípio a criação de uma Custom View se dá de duas maneiras:

1.  Criando uma classe que herde  da classe view, apenas criando um novo comportamento para ela.
2.  Desenvolvendo um layout em XML com uma classe que controle seu comportamento.

[Documentação](https://developer.android.com/guide/topics/ui/custom-components)

### Caso 1 - Customização de um Componente:

 - Criar uma nova classe Java  
 - Implementar a herança de um elemento ou diretamente da classe View 
 - No exemplo será uma Edit Text alterada com o X para limpar o texto  
 - Após definir a herança implementar todos os contrutores necessário/ sugeridos  
 - O preview será exibido  
 - Na pasta drawables adicionar o icone X ( drawable -> add vector -> cliparte --> selecionar a forma)  
 - Adicionar um icone preto e outro cinza ( opacidade 50%)  
 - Define a variável de acesso ao icone  
 - Cria um helper que instancia o icone - init()  
 - Chama o helper nos contrutores  
 - Se herdar da view prcisa desenhar a interface, sobrescrevendo o método onDraw  
 - Quando herda de uma subclasse é possivel apenas sobrescrever para customizar

