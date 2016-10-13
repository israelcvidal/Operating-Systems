#RANDOM GENERATOR OF PROCESSES FILE FOR OPERATING SYSTEMS'S LAB 5
import csv
from random import randint


with open('process.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',', quotechar='#', quoting=csv.QUOTE_MINIMAL)
    writer.writerow(['<arrivalTime>, <processId>, <burstTime>, <priority>'])

    for x in range (0,20):
    	arrivalTime = randint(0, 20)
    	burstTime = randint(0, 20)
    	priority = randint(0, 20)
    	writer.writerow([arrivalTime, x, burstTime, priority ])
    # spamwriter.writerow(['Spam', 'Lovely Spam', 'Wonderful Spam'])