package presentation.converter

import domain.error.Error

object ErrorConverter {
    fun convertErrorToString(error: Error): String {
        return when (error) {
            is Error.GitHubLabelFetchFailed -> "[Error]: Label Fetch Failed from GitHub"
            is Error.GitHubLabelAddFailed -> "[Error]: Label Add Failed from GitHub"
            is Error.GitHubLabelDeleteFailed -> "[Error]: Label Delete Failed from GitHub"
            is Error.GitHubLabelUpdateFailed -> "[Error]: Label Update Failed from GitHub"
        }
    }
}