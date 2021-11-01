package com.example.core.base

sealed class BaseCommand {

    class Error(val errorString: String): BaseCommand()

    class Success(val successMessage: String): BaseCommand()

}