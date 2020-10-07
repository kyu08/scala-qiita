# What is 関数型言語?
## 関数型言語の特徴
### 関数がファーストクラス
ファーストクラスとは、関数を型として扱えるっていうこと。
ファーストクラスの利点
- 関数を引数として渡せる
- 関数の戻り値として返せる

`def`キーワードはメソッドになる。

これは関数リテラルで、関数となる。
```scala
val add = (x: Int, y: Int) => x + y
```

# クロージャにチャレンジ
## クロージャを定義してみる
- 関数Aの戻り値が関数B
- 変数Cは関数A内で宣言されている
- 関数B内では**身元不明の変数Cを宣言なしで使える** 

変数Cを自由変数と呼ぶことにすると、クロージャとは、**自由変数を持つ関数**といえる。
この場合関数Bがクロージャとなる。

```javascript
var candidates = new Array(1,2,3,4,5);

<!-- フィルター関数 -->
var filter = function (predicate) {
    return function(candidates) {
        var numbers = new Array();

        for(var i = 0; i < candidates.length;i++) {
            if(predicate(candidates[i])) {
                numbers.push(candidates[i])
            }
        }

        return numbers;
    };
};

var predicate = function(x) {
    return (x % 2) == 0;
};

<!-- 偶数フィルター関数 -->
var oddFilter = filter(predicate)
var results = oddFilter(candidates);
```

`filter`関数内の無名関数(上でいう関数B)が自由変数`predicate`(変数C)にアクセスできる点が特徴

自由変数には外からアクセスできないため、クロージャを使うと変数と隠蔽化できることがメリットとしてよく挙げられる。(呼び出し側に対して隠蔽できるってことよね？)

# 型
型とは抽象である。

## コンパニオンクラスとコンパニオンオブジェクト
互いに`private`のメンバにアクセスすることができる。

### コラム スタック領域とヒープ領域
スタック領域
確保したのとは逆の順番で解放する。

ヒープ領域
動的に確保と解放を繰り返せるメモリ領域のこと

# Scalaのプリミティブ型
Scalaにはプリミティブ型が存在しない。
全て**オブジェクト**

## Char
文字(**not 文字列**)
**シングルクオートで囲む。**
たとえば
```scala
'a'
'A'
```
など。

## String型
文字列型。
**Charのシーケンス。**(連続・並び)

## リテラル
ソースに値を直接書く書き方。`new`でインスタンス化を行わない略記。

# タプルにチャレンジ
複数の異なる型を返したいときに便利！

### コラム 基本的に List よりも Seq を使おう
```scala
scala> Seq
res0: scala.collection.Seq.type = scala.collection.Seq$@5680a178

scala> List
res1: scala.collection.immutable.List.type = scala.collection.immutable.List$@2d6a9952
```
`Seq`は`scala.collection`下にあるので`Vector`だろうと`MutableList`だろうと`Seq`。
取りうる型の範囲を狭めてしまう`List`などより`Seq`を指定した方がよい。

型アノテーションはこうかく
```scala
def hoge(): (Int, Int) = (1, 3)
```

# トレイト
これできるの知らなかった。
使い道はわからない
```scala
trait Job{ val name: String }
new { val name: String } with Job
```

実装を持つことができる。

# Traversable
## scala.collention.Traversable
このトレイトはコレクションの中でも上位トレイトで重要なメソッドを持っている
コレクション階層の基本となるトレイト。SeqだけではなくSet, Map もこのトレイトをミックスインしている。
`foreach`だけが抽象メソッドで、このメソッド以外はTraversableLikeに実装が用意されている。

### collect
map と filter を合わせたやつ
```scala
Seq(1, 2, 3) collect { case i if (i % 2 == 0) => i }
// List(2)
```

# 第11章 Seq
## scala.collection.immutable.Seq
### idDefinedAt
指定した値が添字に存在するか判定する
```scala
Seq(1, 3, 4) isDefinedAt (2)
// false

Seq(1, 3, 4) isDefinedAt (3)
// true
```

### lengthCompare
要素数が指定した値と比較して
- 少ない場合 -1
- 同じ場合 0
- 多い場合 1

```scala
Seq(1, 2, 3) lengthConpare (2)
// 1
```

# Set
## (x)
xが存在するか判定する

```scala
Set(1,2,3)(1)
// true
```

## contains
(x)と同じ

## 削除

```scala
Set(1,2,3) - 2
// Set(1,3)

// ちなみに
Set(1,2) - 22 // ってやってもエラーにはならず
// Set(1,2) が返される
```

# 第13章 Map
## get
キーに対応する値をOptionで返す

```scala
Map(1 -> "a", 2 -> "b", 3 -> "c") get (1)
// Some(a)
```
## (key)
キーに対応する値を返す
```scala
Map(1 -> "a", 2 -> "b", 3 -> "c")(1)
// "a"
```

## getOrElse
キーに対応する値を返す。なければデフォルト値を返す。
```scala
Map(1 -> "a", 2 -> "b", 3 -> "c") getOrElse (1, "d")
// Some(a)
```

## 追加と更新
### + (要素の追加)
新しいキーバリューのペアを含む新しいMapを返す
```scala
Map(1 -> "a", 2 -> "b", 3 -> "c") + (1, "d")
```

### ++ (Map同士の結合) 

## 削除
+ と -
++ と --
が対応する。

## サブコレクション生成
### keys
キーのiterableをかえす

```scala
Map(1-> "ichi", 2 -> "ni").keys
// Set(1, 2)
```

### values
値のiterableを返す
```scala
Map(1-> "ichi", 2 -> "ni").values
// MapLike.DefaultValuesIterable(ichi, ni)
```

## 変形
### filterKeys
関数を満たすものだけのMapを返す
```scala
Map(1 -> "ichi", 2 -> "ni", 3 -> "san") filterKeys (_ % 2 == 1)
// Map(1 -> "ichi", 3 -> "san")
```

### mapValues
関数を適用したMapを返す
```scala
Map(1 -> "ichi", 2 -> "ni", 3 -> "san") mapValues (_ + "hoge")
// Map(1 -> "ichihoge", 2 -> "nihoge", 3 -> "sanhoge")
```
i
# 第14章 Option型とnull
## Option とは
値があるかないかを表す型。箱のようなイメージ。
### Some
値があることを示す型。値を持っている。

### None
値がないことを表す型。

## 値を取り出す
get で取り出せる

(Noneな値).get はエラーになるので判定が必要。

```scala
def check(o: Option[String]) = {
  o match {
    Some(s) => println(s)
    None => println("None yade.")
  }
}
```

こんなかんじでOptionの値を取り出す時はgetをではなく**パターンマッチを使って取り出そう！**

# 第15章 制御構造
## 変数のスコープ
`{}`内のみで生存できる

scalaでは以下のように中括弧の中で再宣言できる。けど使い道特にないしわかりずらいから避けよう。
```scala
val hoge = 1
{
  val hoge = 2
}
```

## scala の例外
scalaではチェック例外のキャッチが必須ではない。

Javaの例外には2種類あって
- チェック例外
- 非チェック例外

### チェック例外
Exceptionクラスを継承しているクラスをスローして発生させる例外。
`throws`が強制される。(?)
上位では以下のいずれかが必要となる。
- キャッチする
- `throws`を宣言してさらに上位にスローする

### 非チェック例外
RuntimeException クラスを継承しているクラスをスローして発生させる例外。

上位でのキャッチや`throws`宣言は必須ではない。

つまりScalaでは、Javaにおける非チェック例外のように例外を扱うことができる。

# 第16章 Scalaの等価性
## 等価とは
等価を考える上では *等価性*と*同一性*が重要になってくる。

### 等価性
*同値性*と呼ぶこともある。
あるオブジェクトA, Bがあったときに2つが全く同じ値を持つことを等価という。

```scala
val a = new A(1, "a")
val a2 = new A(1, "a")

println(a == a2) // true
```


### 同一性
参照の透過性の意味。
あるオブジェクトA, Bがあったときに2つが同じオブジェクトであれば同一という。

同一性を判定するには`eq`を使用し、同一でないことを判定したい場合は`ne`を使う。

```scala
val a = new A(1, "a")
val a2 = a
val a3 = new A(1, "a")

println(a eq a2) // true
println(a eq a3) // false
```

# まとめると
*等価性*は中身を比較していて*同一性*は参照を比較している。

## ケースクラス
ケースクラスを使うと、`equals`とかの実装が自動で定義される。(class で宣言すると自分で書かないといけない。)

なんだけど、`equals`の実装をするときに少なくとも以下の2パターンはあるので、等価性を比較したいから`case class`を使おうっていうのは浅はか。

- 全フィールドの等価性を確認する必要がある
- 識別子となるフィールドだけ確認すればOK

# パターンマッチ
## シーケンスパターン

```scala
val seq = Seq(1,2,3)

seq match {
  case Seq(1, a, _*) => a
  case _ => 0
}
```

**`_*`は0こ以上の任意の要素を表す。**(めっちゃ便利やん)

## タプルと使う
```scala
val tuple = (1, 2, "OK")

tuple match {
  case (1, 2, x) => x
  case _ => "None"
}
```

## コンストラクタパターン
ケースクラスと一緒に使える！(これもすっきりかけてよさそう！)

```scala
case class Person(val id: Int, val name: String, val food: String)

val person = Person(1, "john", "fruit")

person match {
  case Person(_, "john", "fruit") => "OK"
  case Person(_, "jonn", _) => "BAD"
  case _ => "NO"
}
```

# カリー化と部分適用
カリー化と部分適用は違う。

## カリー化
**複数の引数をとる関数を、1つの引数をとる関数のチェーンに変換すること**
```scala
val add = (x: Int, y: Int, z: Int) => x + y + z
```
っていう関数があったときに
```scala
val addCurried = add.curried
```
ってやるとカリー化できる。

```scala
val addCurriedWithX = addCurried(1)
val addCurriedWithY = addCurriedWithX(2)
val addCurriedWithZ = addCurriedWithY(3)
println(addCurriedWithZ) // 6
```

こんな風に利用する。

### .curried を使う以外の方法
バラしてかく方法や
```scala
def addScalaCurried(x: Int)(y: Int)(z: Int) = x + y + z
```

`_`を使う方法がある。
```scala
val curried = addScalaCurried _
```

## 部分適用
**複数の引数をとる関数に対して、一部の引数に値を束縛した関数を返すこと。**

```scala
def addPartial(x: Int, y: Int, z: Int) = x + y + z
def addPartial(x:Int, y:Int, z:Int) = x + y + z
val addPartialWithZ = addPartial(_:Int, _:Int, 5)
println(addPartialWithZ(1,2)) // 8
```
カリー化は引数のチェーンだったけど、部分適用はいきなり第3引数を与えたりできる。

# 第20章 Scala でアクター
## アクターとは
オブジェクト指向のオブジェクトっぽいもの。
並行性を備えている。これがOOPとの最大の違い。

## メッセージ
エンティティ間を繋ぐもの。OOPでもそこは同じだよね。
けど直接やり取りしているわけではなく、メールボックス経由でやりとりを行なっている。

## メールボックス
メッセージを入れておく箱。アクターが持っている。

# 第21章 型パラメータ
ジェネリクスのことをScalaでは型パラメータという。

# 第22章 抽出子
`unapply`メソッドが定義されているオブジェクトのこと。








