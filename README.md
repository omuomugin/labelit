# GitHub labels as Code

`labelit` is a tool to manage GitHub label as code.

You can sync labels for multiple repository with just single command per repository.

# Supported Command

```shell
Usage: label [OPTIONS] COMMAND [ARGS]...

Options:
  -h, --help  Show this message and exit

Commands:
  show    show current set labels with target repo
  update  update all the labels in the target repo with deleting current ones
```

# Quick Start

You need a config file written in yaml. (other type will be supported in near future maybe).

name and color are required and description is optional.  
description will be empty when not given.

```yaml
labels:
    - name: bug
      color: fc2929
      description: this is bug
    - name: help wanted
      color: 000000
    - name: fix
      color: cccccc
    - name: good first issue
      color: fbca04
```

```shell
labelit update --token=XXXXX --repo=omuomugin/labelit --config=path/to/your/config.yaml
```

# Technical Things

Whole CLI tool is written in Kotlin.

And I want to thank all of the great libraries out there.

- [ajalt/clikt](https://github.com/ajalt/clikt) for basic CLI implementation
- [kittinunf/fuel](https://github.com/kittinunf/fuel) for Http Request
- [charleskorn/kaml](https://github.com/charleskorn/kaml) for serializing, desirializing YAML
  

## How to start it locally

Thanks to [johnrengelman/shadow](https://github.com/johnrengelman/shadow) , it is easy to build fat jar which can use it from command line.

```shell
./gradlew clean shadowjar
```

and you just run jar with command.

```shell
java -jar build/libs/labelit-x.y.z-all.jar --help
```
