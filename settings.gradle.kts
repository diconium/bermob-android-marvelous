//region refreshVersions
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.1"
////                            # available:"0.40.2"
}

refreshVersions {
    enableBuildSrcLibs()
    rejectVersionIf {
        candidate.stabilityLevel != de.fayard.refreshVersions.core.StabilityLevel.Stable
    }
}
//endregion

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Bermob Marvelous"
include(":app")
