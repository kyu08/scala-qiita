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













