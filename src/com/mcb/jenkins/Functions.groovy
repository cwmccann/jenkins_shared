
/**
 * Gets all the branches for a project
 */
def getBranches(repository) {
    def gettags = ("git ls-remote -t -h ${repository}").execute()
    return gettags.text.readLines().collect { 
        it.split()[1].replaceAll('refs/heads/', '').replaceAll('refs/tags/', '').replaceAll("\\^\\{\\}", '')
    }
}

//println getBranches("git@github.com:miwdesign/mcb-testcases.git")