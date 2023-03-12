from matplotlib import pyplot as plt
from matplotlib.colors import ListedColormap
import numpy as np

def annotateAll():
    for i in range(len(xpos)):
        plt.annotate(i, (xpos[i], ypos[i]))

particles = {}
neighbors = {}
xpos = np.array([])
ypos = np.array([])

fig, ax = plt.subplots()

with open('../../../input.txt') as f:
    index = 0
    lineCount = 0
    for line in f.readlines():
        data = line.split()
        if lineCount != 0:
            particles[index] = [float(data[0]), float(data[1])]
            xpos = np.append(xpos, float(data[0]))
            ypos = np.append(ypos, float(data[1]))
            circle = plt.Circle((float(data[0]), float(data[1])), 0.5, color='r', fill=False)
            ax.set_aspect('equal', adjustable='datalim')
            ax.add_patch(circle)
            index += 1
        lineCount += 1

print(particles)

with open('../../../output.txt') as f:
    for line in f.readlines():
        data = line.split()
        index = int(data[0])
        neighbors[index] = []
        for i in range(1, len(data)):
            neighbors[index].append(int(data[int(i)]))

print(particles)
print()
print(neighbors)

# plot x and y positions as circles with radius 0.1
plt.scatter(xpos, ypos, s=10, c='b', marker='o', alpha=0.5)
annotateAll()
plt.xlim(0, 2)
plt.ylim(0, 2)
plt.grid()
plt.show()

for i in range(len(xpos)):
    plt.scatter(xpos, ypos, s=10, c='b', marker='o', alpha=0.5)
    plt.plot(xpos[i], ypos[i], c='g', marker='o', alpha=0.5)
    for n in neighbors[i]:
        print(particles[n])
        plt.plot(particles[n][0], particles[n][1], c='r', marker='o', alpha=0.5)
    annotateAll()
    plt.xlim(0, 2)
    plt.ylim(0, 2)
    plt.grid()
    plt.show()
