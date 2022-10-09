#!/usr/bin/python3
"""reducer_var.py"""

import sys

ck = 0
mk = 0
vk = 0

for row in sys.stdin:
    try:
        ci, mi, vi = map(float, row.split())
    except:
        continue
    vk = (ci * vi + ck * vk) / (ci + ck) + ci * ck * ((mk - mi) / (ci + ck)) ** 2
    mk = (ci * mi + ck * mk) / (ci + ck)
    ck += ci

    print(ck, mk, vk)