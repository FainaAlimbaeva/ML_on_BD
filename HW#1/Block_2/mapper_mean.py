#!/usr/bin/python3
""" mapper_mean.py """

import sys
import csv


for row in csv.reader(sys.stdin):
    price = row[9]
    print("1", price)
