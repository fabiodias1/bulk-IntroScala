// Databricks notebook source
// MAGIC %md
// MAGIC 
// MAGIC # Iniciando com Scala no Databricks

// COMMAND ----------

// MAGIC %md
// MAGIC ## Introdução
// MAGIC 
// MAGIC Scala é uma linguagem baseada em Java com propósito de facilitar o desenvolvimento de aplicações em comparação com Java. Para isso ela emprega alguns requisitos originários do paradigma funcional.  
// MAGIC 
// MAGIC Scala roda na máquina virtual Java (JVM), por isso podendo utilizar classes do Java e podendo ser executada nas mais diversas plataformas.  
// MAGIC 
// MAGIC Com Scala é possível desenvolver aplicações usando *Orientação a Objetos* ou o *paradigma funcional*.  
// MAGIC 
// MAGIC Este notebbok tem como objetivo mostrar uma introdução a linguagem Scala no ambiente do Databricks, por isso os exemplos não ficaram limitados a determinado paradigma. A maioria dos exemplos podem ser executados sem alterações através da ferramenta "Scala REPL", um interpretador de linha de comando para a linguagem Scala, contudo eles foram elaborados para serem executados em notebooks do Databricks com a linguagem Scala.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Hello World
// MAGIC 
// MAGIC Muitos tutoriais e livros de programação começam como mostrar uma imagem na tela, geralmente com o texto "Hello World" (Olá mundo!). Então para manter a tradição segue um exemplo abaixo:

// COMMAND ----------

println("Hello World!")

// COMMAND ----------

// MAGIC %md Hello world com *objeto singleton* (Semelhante a programação Java)

// COMMAND ----------

object Hello {
    def main(args: Array[String]) = {
        println("Hello world")
    }
}

Hello.main(Array(""))

// COMMAND ----------

object Hello1 {
    def sayHello() = {
        println("Hello wonderfull world")
    }
}

Hello1.sayHello()

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Entrada de dados

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Para capturar dados em um notebook no databricks, é necessário a criação de "wigdets" para o notebook.

// COMMAND ----------

// MAGIC %md Criando Wigdets

// COMMAND ----------

//dbutils.widgets.text("Nome_widget", "Valor padrão", "Rótulo (label)")
dbutils.widgets.text("txt_nome", "Fulano", "Seu nome")

//dbutils.widgets.dropdown("Nome_widget", "Valor padrão", "Lista de valores", "Rótulo (label)")
dbutils.widgets.dropdown("ddw_uf", "SP", Seq("SP", "RJ", "ES", "MG") , "Informe a UF onde reside")

// COMMAND ----------

// MAGIC %md No topo do notebook aparecem os componentes para entradas de dados.

// COMMAND ----------

// MAGIC %md Capturando os dados e exibindo no notebook

// COMMAND ----------

val nome = dbutils.widgets.get("txt_nome")
val uf   = dbutils.widgets.get("ddw_uf")

println(s"O nome informado é $nome")
println(s"A UF informada é $uf")

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC *Nota*: Toda vez que o valor do widget é mudado, a célula acima é executada novamente.

// COMMAND ----------

// MAGIC %md apagando os wigdets.

// COMMAND ----------

dbutils.widgets.remove("txt_nome")
dbutils.widgets.remove("ddw_uf")

// COMMAND ----------

// MAGIC %md 
// MAGIC 
// MAGIC ## Declaração de variáveis
// MAGIC 
// MAGIC Scala permite a declaração de variáveis sem determinar o tipo, semelhante ao Python e JavaScript, ou forçando a tipagem como em Java ou C.  

// COMMAND ----------

// MAGIC %md Variáveis sem tipagem pré-definida

// COMMAND ----------

var variavel1 = "Teste 1.0"
println(variavel1)

variavel1 = "Teste 1.1"
println(variavel1)

// COMMAND ----------

// MAGIC %md Contudo a tipagem não é dinâmica, logo em caso de tentativa de armazenamento um dado de tipo diferente em uma variável já utilizada, um error é disparado.

// COMMAND ----------

variavel1 = 2 + 23
println(variavel1)

// COMMAND ----------

// MAGIC %md Com tipagem definida

// COMMAND ----------

var variavel2: String = "Teste 2.0"
var variavel2a: String = ""

variavel2a = "Teste 2.1"

print(variavel2 + "\n" + variavel2a + "\n")

// COMMAND ----------

// MAGIC %md Obrigatoriamente, ao declarar uma variável, deve-se inicializa-lá com algum valor

// COMMAND ----------

var variavel2b: String

// COMMAND ----------

// MAGIC %md Ao tentar armazenar um tipo diferente de dado em uma variável com tipo determinado

// COMMAND ----------

var variavel3a : String = ""

variavel3a = 2

// COMMAND ----------

// MAGIC %md Constantes

// COMMAND ----------

val constante1a = "Valor Constante"
val constante_euler : Double =  2.718

println(constante1a)
println(constante_euler)

// COMMAND ----------

// MAGIC %md Por ser uma constante, não é possível alterar o valor dela.

// COMMAND ----------

constante1a = "Valor constante 1a"

// COMMAND ----------

// MAGIC %md **Nota**: É possível alterar o valor de uma constante declarando-a novamente.

// COMMAND ----------

val constante1a : String = "Valor Constante 1a"

println(constante1a)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC *Nota*: Na linguagem Scala todos os valores são objetos, inclusive os tipos primitivos

// COMMAND ----------

// MAGIC %md
// MAGIC ## Estruturas de controles

// COMMAND ----------

// MAGIC %md ### Se/então (if/esle)

// COMMAND ----------

var valor_teste : Int = 2

if (valor_teste < 10) {
    println("Menor que 10")
} else if (valor_teste > 10) {
    println("Maior que 10")
} else {
    println("Igual à 10")
}

// COMMAND ----------

// MAGIC %md ### Operador ternário
// MAGIC 
// MAGIC Como tudo em Scala é um objeto, a estrutura *if/else* sempre retorna um valor, por isso é possível utilizá-los como operador ternário.

// COMMAND ----------

var texto_teste1 = if (valor_teste < 10) "Menor que 10." else "Maior ou igual a 10"

println(texto_teste1)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### match expressions (switch case)

// COMMAND ----------

val texto_teste2 = valor_teste match {
    case 1 => "Um"
    case 2 => "Dois"
    case _ => "Nem 1 e nem 2"
}

println(texto_teste2)

// COMMAND ----------

def whatClass(x: Any):String = x match {
    case s: String => s + " é uma String"
    case i: Int => "É um inteiro"
    case f: Float => "É um Float"
    case d: Double => "É um Double"
    case l: List[_] => "É uma List"
    case _ => "Desconhecido"
}

// COMMAND ----------

// MAGIC %md Exemplo acima extraído de *SCALA BOOK - PRELUDE꞉ A TASTE OF SCALA* - Adaptado.

// COMMAND ----------

val const_pi : Float = 3.14.toFloat

println(whatClass("Oi"))
println(whatClass(2))
println(whatClass(3.14))
println(whatClass(const_pi))
println(whatClass(("Oi", 3)))
println(whatClass(List(1, 3)))
println(whatClass(List("Oi", 3)))

// COMMAND ----------

// MAGIC %md ### Try/Catch

// COMMAND ----------

try {
  println("Sem problemas de execução")
  var teste1 = 1
} catch {
    case _: Any => println("Erro inesperado")
}

// COMMAND ----------

try {
  println("Sem problemas de execução - passo 1")
  var teste2 = 2 / 0 //Erro: Divisão por zero(0)
  println("Sem problemas de execução - passo 2")
  
} catch {
    case _: Any => println("Erro inesperado")
}

// COMMAND ----------

// MAGIC %md ### Iterações (laços/loops)

// COMMAND ----------

// MAGIC %md #### De para (For)

// COMMAND ----------

for (i <- 0 to 5) println(i)

// COMMAND ----------

var int_j : Int = 0

for (i <- 0 to 2) { 
  int_j = int_j + i
  println(int_j)
}

// COMMAND ----------

for (i <- 0 to 6 by 2) println(i)

// COMMAND ----------

var list_teste = List(1, 3, 5, 7, 9)

for (item <- list_teste) println(item)

// COMMAND ----------

val const_x = for (i <- 1 to 5) yield i * 2

println(const_x)

// COMMAND ----------

// MAGIC %md
// MAGIC #### Enquanto (while)

// COMMAND ----------

var variavel_x = 1

while (variavel_x < 5) {
  println(variavel_x)
  variavel_x += 1
}

// COMMAND ----------

// MAGIC %md #### Repita (do/while)

// COMMAND ----------

do {
  println(variavel_x)
  variavel_x += 1
} while (variavel_x < 10)

// COMMAND ----------

// MAGIC %md
// MAGIC ## Validações e testes com *assert()*
// MAGIC 
// MAGIC A função `assert(argumento, mensagem)` avalia se a expressão informada em `argumento` retorna o valor booleano de "Verdadeiro". Se o valor for diferente de verdadeiro, ela dispara um erro e mostra para o usuário a mensagem contida em `mensagem`.  
// MAGIC Essa função é muito usada em testes e validações.

// COMMAND ----------

var bolA : Boolean = true

assert(bolA, "Valor inválido!")
println("Tudo OK!")

// COMMAND ----------

assert(!bolA, "Valor inválido!")
println("Tudo OK!")

// COMMAND ----------

// MAGIC %md
// MAGIC ## Funções personalizadas

// COMMAND ----------

// MAGIC %md
// MAGIC A função **soma1** recebe 2 inteiros como parâmetros, e devolve um inteiro. O segundo parâmetro da função possui o valor padrão de zero.

// COMMAND ----------

def soma1(int_a: Int, int_b: Int = 0) : Int = {
  return int_a + int_b
}

println(soma1(2, 1))

println(soma1(2))

// COMMAND ----------

// MAGIC %md Para funções feitas pelo programador(a), é necessário indicar o tipo do parâmetro.

// COMMAND ----------

def soma2(int_a, int_b = 0) = {
  return int_a + int_b
}

// COMMAND ----------

// MAGIC %md Se a função retornar algum valor, deve-se indicar o tipo do valor.

// COMMAND ----------

def soma2(int_a: Int, int_b: Int) = {
  return int_a + int_b
}

// COMMAND ----------

// MAGIC %md Caso contrário, não é necessário.

// COMMAND ----------

def soma2(int_a: Int, int_b: Int) = {
  println( int_a + int_b )
}

soma2(2, 3)

// COMMAND ----------

// MAGIC %md
// MAGIC ## Classes - Uma simples introdução
// MAGIC 
// MAGIC Exemplo de Classe em Scala

// COMMAND ----------

class Contato(
  var nome: String, 
  var telefone: String
) {
    def exibeInfo() = println(s"Informações do contato:\nNome: $nome\nTelefone: $telefone")
}

// COMMAND ----------

val contato1 = new Contato("Fulano", "(99)99999-9999")
contato1.exibeInfo

println(contato1.nome)

// COMMAND ----------

// MAGIC %md Herança

// COMMAND ----------

class Cliente 
( 
  var cliente_nome: String,
  var cliente_telefone: String,
  var cliente_trat: String = ""
) 
extends Contato(cliente_nome, cliente_telefone)
{
  var tratamento : String = if (cliente_trat == "") "Sr(a)." else cliente_trat
  private var cpf : String = "" //Atributo privado
  
  override def exibeInfo() = println(s"Informações do cliente:\nNome: $tratamento $nome\nTelefone: $telefone\nCPF: $cpf")
  
  def setCpf(cpf_novo : String ) = {
    cpf = cpf_novo
  }
  
  def getCpf(): String =  { return cpf }
}

// COMMAND ----------

val cliente1 = new Cliente("Fulana", "(99)99999-9988")

cliente1.exibeInfo()

// COMMAND ----------

// MAGIC %md
// MAGIC Atributos sem valores pré-definidos, devem ter seus valores informados no momento da criação do objeto.

// COMMAND ----------

val cliente2 = new Cliente("Ciclano")

// COMMAND ----------

val cliente2 = new Cliente("Ciclano", "(88)88888-9999", "Sr.")

cliente2.exibeInfo

// COMMAND ----------

// MAGIC %md
// MAGIC Tentando acessar atributos privados.

// COMMAND ----------

println(cliente2.cpf)

// COMMAND ----------

cliente2.setCpf("111.111.111-01")

println(cliente2.getCpf)
println("=======================")

cliente2.exibeInfo

// COMMAND ----------

// MAGIC %md Testando se determinado objeto pertence a uma classe.

// COMMAND ----------

def whatClass_v2(x: Any):String = x match {
    case s: String => s + " é uma String"
    case i: Int => "É um inteiro"
    case f: Float => "É um Float"
    case d: Double => "É um Double"
    case l: List[_] => "É uma List"
    case c: Contato => "É um objeto da Contato"
    case _ => "Desconhecido"
}

// COMMAND ----------

println(whatClass_v2(1))
println(whatClass_v2("Teste"))
println(whatClass_v2(contato1))
println(whatClass_v2(cliente1))

// COMMAND ----------

// MAGIC %md ## Objetos singleton

// COMMAND ----------

// MAGIC %md
// MAGIC   
// MAGIC Métodos e valores que não são associados com instâncias individuais de uma classe são considerados **objetos singleton**, denotados através da palavra-chave `object` ao invés de `class`.  
// MAGIC Fonte: TOUR OF SCALA - OBJETOS SINGLETON.

// COMMAND ----------

object Animal {
  var patas : Int = 4
  def run() = println("Correndo")
}

Animal.run()

println(Animal.patas)

// COMMAND ----------

// MAGIC %md ## Coleções de dados

// COMMAND ----------

// MAGIC %md
// MAGIC Scala possui vários tipos de coleções de dados, dentre elas pode-se destacar:
// MAGIC - Listas (List);
// MAGIC - Vector;
// MAGIC - ArrayBuffer;
// MAGIC - Map;
// MAGIC - Set.

// COMMAND ----------

// MAGIC %md 
// MAGIC 
// MAGIC ### ArrayBuffer
// MAGIC 
// MAGIC *ArrayBuffer* é uma sequência mutável, indexada de dados.

// COMMAND ----------

import scala.collection.mutable.ArrayBuffer

var arr_teste1 : ArrayBuffer[Any] = ArrayBuffer(1, 2, 3)

println(arr_teste1(0))

arr_teste1.append("4")

arr_teste1(0) = "0"

println(arr_teste1)

// COMMAND ----------

// MAGIC %md
// MAGIC ### Lista(List)
// MAGIC 
// MAGIC *Lista("List")* é uma sequência linear(ligada ou com ligação entre cada elemento) e imutável de dados.

// COMMAND ----------

var lst_teste1 : List[Any] = List(1, "2", 3)

println("Primeiro elemento", lst_teste1(0))

println(lst_teste1)

// COMMAND ----------

lst_teste1(0) = "0"

// COMMAND ----------

// MAGIC %md 
// MAGIC ### Vector
// MAGIC 
// MAGIC *Vector* é uma sequencia indexada e imutável de dados. Entende-se por "indexada" a capacidade acessar rapidamente quaisquer elementos da sequência, indiferente de sua posição, diferentemente da *Lista(List)* cuja a velocidade do acesso depende da quantidade de elementos.

// COMMAND ----------

val vct_teste1 : Vector[Any] = Vector(1, 2.3, 3)

println("Primeiro elemento", vct_teste1(0))

println(vct_teste1)

// COMMAND ----------

vct_teste1(1) = "2"

// COMMAND ----------

// MAGIC %md 
// MAGIC ### Map
// MAGIC 
// MAGIC *Map* é conjunto de dados organizados em  pares "chave e valor", semelhante a um dicionário em Python ou a estrutura de um JSON . Essa coleção possui uma versão mutável e outra imutável.
// MAGIC 
// MAGIC *Nota*: No Databricks a versão imutável de Map é disponizado por padrão, sem  necessidade de importação.

// COMMAND ----------

val mapContato = Map(
    "Nome" -> "Alice",
    "Idade" -> 38,
)

// COMMAND ----------

// MAGIC %md Acessando determinada chave

// COMMAND ----------

mapContato("Nome")

// COMMAND ----------

mapContato("Nome") = "Antonio"

// COMMAND ----------

// MAGIC %md 
// MAGIC ### Set
// MAGIC 
// MAGIC *Set* é uim cojunto de dados sem valores duplicados. Assim como Map, possui uma versão mutável e outra imutável.  
// MAGIC *Nota*: No Databricks a versão imutável de Set é disponizado por padrão, sem  necessidade de importação.

// COMMAND ----------

val set_dados1 : Set[Any] = Set("Alice", 38)

// COMMAND ----------

set_dados1(0) = "Abgail"

// COMMAND ----------

val set_dados2 : Set[Int] = Set(37, 38, 37)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### Seq
// MAGIC 
// MAGIC Seq é um *trait* que  representa sequências. Assim como Map, possui uma versão mutável e outra imutável.  
// MAGIC *Nota*: No Databricks a versão imutável de Set é disponizado por padrão, sem  necessidade de importação.

// COMMAND ----------

val seq_teste1 : Seq[Any] = Seq(1, "2")

seq_teste1

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC *Nota*: Similar a interfaces em Java, traits são utilizadas para definir tipos de objetos apenas especificando as assinaturas dos métodos suportados.  
// MAGIC Fonte: TOUR OF SCALA - TRAITS.

// COMMAND ----------

// MAGIC %md
// MAGIC ### Algumas funções ou métodos para trabalhar com coleções

// COMMAND ----------

// MAGIC %md
// MAGIC Exemplo de funções do usuário para tratamento dos dados das coleções.

// COMMAND ----------

def menor_que_cinco(int_a: Int) : Boolean = {
  if (int_a < 5)
    return true
  
  return false
}

def obtem_quadrado(int_a: Int) : Int = {
  return int_a * int_a
}


// COMMAND ----------

// MAGIC %md 
// MAGIC Coleção de dados

// COMMAND ----------

val numeros = (1 to 10).toList

// COMMAND ----------

// MAGIC %md Função *foreach*

// COMMAND ----------

numeros.foreach(println)

// COMMAND ----------

// MAGIC %md Função *map*

// COMMAND ----------

numeros.map(obtem_quadrado).foreach(println)


// COMMAND ----------

var var_a0 = numeros.map(obtem_quadrado)

println(var_a0)

// COMMAND ----------

// MAGIC %md Função *filter*

// COMMAND ----------

numeros.filter(menor_que_cinco).foreach(println)

// COMMAND ----------

var var_a1 = numeros.filter(menor_que_cinco)

println(var_a1)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Funções anônimas
// MAGIC 
// MAGIC Em caso de necessidade de realizar processamento complexo apenas uma vez para determinada coleção de dados, é possível escrever *funções anônimas*  

// COMMAND ----------

numeros.filter(_ < 5).foreach(println)

// COMMAND ----------

numeros.map(_ * 2).foreach(println)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Também é possível escrever funções anônimas das seguintes formas:

// COMMAND ----------

numeros.map((x: Int) => x * 2).foreach(println)

// COMMAND ----------

numeros.map(x => x * 2).foreach(println)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Usando bibliotecas do Java
// MAGIC 
// MAGIC Como Scala usa JVM do Java, é possível usar as bibliotecas da linguagem Java.  
// MAGIC Abaixo segue um exemplo usando bibliotecas para trabalhar com datas.  

// COMMAND ----------

//Importação das bibiotecas
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//Criação dos objetos
val dataHoraAtual : LocalDateTime = LocalDateTime.now
val dateTime_Formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

val str_texto_dataHora : String = dataHoraAtual.format(dateTime_Formatter)

println(s"Data e Hora atual: $str_texto_dataHora\n")

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ## Referências
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - PRELUDE꞉ A TASTE OF SCALA *Tradução: Livro sobre Scala - Prelúdio: Um pedaço de Scala*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/prelude-taste-of-scala.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: Conheça a Linguagem Scala  
// MAGIC Autor(es): Eduardo Felipe Zambom Santana (https://www.devmedia.com.br/perfil/eduardo-felipe-zambom-santana)  
// MAGIC Data de publicação: Ano de 2015.  
// MAGIC Site: https://www.devmedia.com.br/conheca-a-linguagem-scala/32850  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: Databricks Utilities, seção Widgets utility (dbutils.widgets)  
// MAGIC Data de publicação: 16 Julho de 2021  
// MAGIC Autor(es): Databricks, Inc  
// MAGIC Site: https://docs.databricks.com/dev-tools/databricks-utils.html  
// MAGIC Data de acesso: 10 de agosto de 2021   
// MAGIC 
// MAGIC Artigo / Texto: TOUR OF SCALA - TRAITS  
// MAGIC Autor(es): Heather Miller (Github: heathermiller), mghildiy (Github: mghildiy), Aaron S. Hawley (Github: ashawley) e Chris Kipp (Github: ckipp01)  
// MAGIC Tradutor(es): Desconhecido.  
// MAGIC Site: https://docs.scala-lang.org/pt-br/tour/traits.html  
// MAGIC Data de acesso: 10 de agosto de 2021  
// MAGIC 
// MAGIC Artigo / Texto: TOUR OF SCALA - OBJETOS SINGLETON  
// MAGIC Autor(es): Heather Miller (Github: heathermiller), Chris Kipp (Github: ckipp01), Dirceu Semighini Filho, Seth Tisue (Github: SethTisue), Horimoto Yasuhiro (Github: komainu8) e Aaron S. Hawley (Github: ashawley)  
// MAGIC Tradutor(es): Provalvelmente o Sr. Dirceu Semighini Filho.  
// MAGIC Site: https://docs.scala-lang.org/pt-br/tour/singleton-objects.html  
// MAGIC Data de acesso: 10 de agosto de 2021  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - SCALA COLLECTIONS *Tradução: Livro sobre Scala - Coleções*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/collections-101.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - THE ARRAYBUFFER CLASS *Tradução: Livro sobre Scala - A classe ArrayBuffer*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj), Qiu Yang Nie (Github: qiuyangnie) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/arraybuffer-examples.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - THE LIST CLASS *Tradução: Livro sobre Scala - A classe Lista*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/list-class.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - THE VECTOR CLASS *Tradução: Livro sobre Scala - A classe Vetor*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj), Manish Bansal (Github: manishbansal8843) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/vector-class.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - THE MAP CLASS *Tradução: Livro sobre Scala - A classe Map*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/map-class.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - THE SET CLASS *Tradução: Livro sobre Scala - A classe Set*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj), 0x54321 (Github: 0x54321) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/vector-class.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: SCALA BOOK - ANONYMOUS FUNCTIONS *Tradução: Livro sobre Scala - Funções Anônimas*  
// MAGIC Autor(es): Alvin Alexander (Github: alvinj), nathanknox (Github: nathanknox) e Meriam Lachkar (Github: mlachkar)  
// MAGIC Site: https://docs.scala-lang.org/overviews/scala-book/anonymous-functions.html  
// MAGIC Data de acesso: 10 de agosto de 2021.  
// MAGIC 
// MAGIC 
// MAGIC Artigo / Texto: Seq, de Scala programming documentation.   
// MAGIC Site: https://weblab.tudelft.nl/docs/scala/2.12/scala/collection/mutable/Seq.html  
// MAGIC Data de acesso: 11 de agosto de 2021.  
// MAGIC 
// MAGIC Artigo / Texto: Seq, de Scala programming documentation.   
// MAGIC Site: https://weblab.tudelft.nl/docs/scala/2.12/scala/collection/immutable/Seq.html  
// MAGIC Data de acesso: 11 de agosto de 2021.  
