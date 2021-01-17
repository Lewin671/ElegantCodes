package com.example.elegantcode.dsl

class Dependency {
    val libraries = ArrayList<String>()
    fun implementation(lib: String) {
        libraries.add(lib)
    }
}

fun dependencies(block: Dependency.() -> Unit): List<String> {
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}

fun main() {
    val libraries = dependencies {
        implementation("androidx.appcompat:appcompat:1.2.0")
        implementation("com.google.android.material:material:1.2.1")
    }

    for (lib in libraries) {
        println(lib)
    }
}