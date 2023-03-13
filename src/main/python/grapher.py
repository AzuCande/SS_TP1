from matplotlib import pyplot as plt
from matplotlib.colors import ListedColormap
import numpy as np

particles = {}
neighbors = {}
xpos = np.array([])
ypos = np.array([])

fig, ax = plt.subplots()

# with open('../../../input.txt') as f:
with open('../resources/dynamicInput_2.txt') as f:
    index = 0
    lineCount = 0
    for line in f.readlines():
        data = line.split()
        if lineCount != 0:
            particles[index] = [float(data[0]), float(data[1])]
            xpos = np.append(xpos, float(data[0]))
            ypos = np.append(ypos, float(data[1]))
            circle = plt.Circle((float(data[0]), float(data[1])), 1, color='r', fill=False)
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

#plot x and y positions as circles with radius 0.1
size = 0.25/0.1 * 10
plt.scatter(xpos, ypos, s=size, c='b', marker='o', alpha=0.5)
for i in range(len(xpos)):
    # The 1st argument is the annotation label, 2nd is the coordinate of the annotation
    plt.annotate(i, (xpos[i], ypos[i]))
plt.grid()
plt.show()