Each directory name under "references" should exactly match the name of a reference to which those config files will be deployed ("production", "staging", ...).  The "default" directory is the one that will be used when a config .deb is installed without providing a reference name, and (probably) for when running on your laptop.

See https://fch.ldschurch.org/confluence/display/Help/CM+2.0+Application+Configuration+Strategy for details on the reference usage.

The pom.xml should be left alone; it is used by the CM tools when turning the config files into a .deb.
