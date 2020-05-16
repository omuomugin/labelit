# Supported Command
- show : show labels write now
- add : add if no conflict
- add -f : force add with over writing conflict ones  
- update : delete all the labels and adds

# config file
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
  - name: notes
    color: fbca04
```