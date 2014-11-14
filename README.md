ofexport2
=================

A successor to ofexport - still a very early prototype.

A tool for exporting omnifocus data to a variety of file formats.

The original ofexport was written in python and relied on direct access
to the OmniFocus database. This was efficient but entirely unsupported.

This new version is written in java and by means of an osascript bridge
accesses the Omnifocus data directly using the OmniGroup supported
OSA script (actually JavaScript) interface.

command line ideas:

C="c1" ...
P="p1" ...
I
avail=[all|available|remaining|completed]
flagged=[true|false]
filt="{...}"
struct=s
fmt=f