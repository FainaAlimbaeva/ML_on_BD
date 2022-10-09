#!/usr/bin/python3
""" mapper_var.py """

import sys
import csv


for row in csv.reader(sys.stdin):
    price = row[9]
    print("1", price, "0")
