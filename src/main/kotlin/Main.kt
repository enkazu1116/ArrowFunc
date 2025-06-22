package org.kotlin

import arrow.core.Either
import arrow.core.left
import arrow.core.right
//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
fun main() {
    when(sampleFunction()) {
        is Either.Right -> {
            // 成功時
            println("成功")
        }
        is Either.Left -> {
            // 失敗
            println("失敗")
        }
    }
}

private fun sampleFunction(): Either<RuntimeException, String> {
    if (true) {
       return "success".right()
    } else {
        return RuntimeException("fall").left()
    }
}