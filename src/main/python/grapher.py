from matplotlib import pyplot as plt
from matplotlib.colors import ListedColormap
import numpy as np

x_positions = []
y_positions = []

with open('../../../data.txt') as f:
    for line in f.readlines():
        positions = line.split()
        x_positions.append(float(positions[0]))
        y_positions.append(float(positions[1]))

cmap = ListedColormap(['w'])
a = np.diag(range(2))
cax = plt.matshow(a,cmap=cmap)
plt.scatter(x_positions,y_positions)
plt.show()