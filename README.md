# Draconic Evolution Fusion Crafting Recipe Changes

# Quick Start

You'll need Eclipse or IntelliJ to properly edit the files found in this repository. Ensure that JDK 8 is installed as well. If you don't know what this is, then search for "adopt open jdk 8" online. Don't use the latest version of the Java Development Kit for this (JDK 17 as of the time of this writing), as Minecraft 1.12.2 doesn't support newer versions.

To set up this repository for editing in your preferred editor, type in the following command:
```
gradlew setupDecompWorkspace
```
If you are using Eclipse, also type this command:
```
gradlew eclipse
```
To build the project, type the following command in the Command Prompt inside the folder of this repository:
```
gradlew build
```
When you edit the recipes, don't forget to increase the build number and the mod version inside the `build.properties` file, then run the above command to rebuild the project.

Your final build will be located in `/build/libs/defusioncraftingrecipechanges-<minecraftVersion>-<versionNumber>.jar`.