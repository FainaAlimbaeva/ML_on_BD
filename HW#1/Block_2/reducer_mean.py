#!/usr/bin/python3
"""reducer_mean.py"""

import sys

ck = 0
mk = 0

for row in sys.stdin:
    try:
        ci, mi = map(float, row.split())
    except:
        continue
    mk = (ci * mi + ck * mk) / (ci + ck)
    ck += ci

    print(ck, mk)