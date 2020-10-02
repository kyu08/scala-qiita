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

# オブジェクト指向

























