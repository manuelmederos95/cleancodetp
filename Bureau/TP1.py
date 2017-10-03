import csv
import json
import time

def function(name):
	x = open(name + '.json').read()
	x = json.loads(x)
	f = csv.writer(open(name + "-" + time.strftime('%Y-%m-%d %H:%M:%S') + ".csv", "wb+"))


	for x in x["list"]:
		f.writerow([x["host"], x["path"], x["code"], x["durationMs"]])
