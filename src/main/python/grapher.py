from matplotlib import pyplot as plt
from matplotlib.colors import ListedColormap
import numpy as np

particles = {}
neighbors = {}
xpos = np.array([])
ypos = np.array([])

with open('../../../input.txt') as f:
    index = 0
    lineCount = 0
    for line in f.readlines():
        data = line.split()
        if lineCount != 0:
            particles[index] = [float(data[0]), float(data[1])]
            xpos = np.append(xpos, float(data[0]))
            ypos = np.append(ypos, float(data[1]))
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
plt.scatter(xpos, ypos, s=10, c='b', marker='o', alpha=0.5)
plt.grid()
# cmap = ListedColormap(['w'])
# a = np.diag(range(2))
# cax = plt.matshow(a,cmap=cmap)
# plt.scatter(x_positions,y_positions)
plt.show()