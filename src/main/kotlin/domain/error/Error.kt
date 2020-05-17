package domain.error

sealed class Error {
    class GitHubLabelFetchFailed : Error()
    class GitHubLabelAddFailed : Error()
    class GitHubLabelDeleteFailed : Error()
}