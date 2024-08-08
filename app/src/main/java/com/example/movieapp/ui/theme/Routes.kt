package com.example.movieapp.ui.theme

sealed class Routes(val routes: String) {
    object Landing : Routes("landing")
    object ListAll : Routes("listall")

    object Detail : Routes("detail")
}