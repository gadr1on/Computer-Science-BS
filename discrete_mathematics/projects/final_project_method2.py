import scipy.spatial.distance as ssd
import scipy.cluster.hierarchy as sch
import matplotlib.pyplot as plt

distMatrix =  [[  0  ,  31 ,  58 ,  27 ,  12 ,  10 ,  34 ,  64 ,  44 ],
               [  31 ,  0  ,  83 ,  21 ,  36 ,  45 ,  35 ,  92 ,  102],
               [  58 ,  83 ,  0  ,  66 ,  11 ,  78 ,  28 ,  39 ,  94 ],
               [  27 ,  21 ,  66 ,  0  ,  88 ,  96 ,  67 ,  49 ,  50 ],
               [  12 ,  36 ,  11 ,  88 ,  0  ,  43 ,  12 ,  33 ,  70 ],
               [  10 ,  45 ,  78 ,  96 ,  43 ,  0  ,  23 ,  46 ,  83 ],
               [  34 ,  35 ,  28 ,  67 ,  12 ,  23 ,  0  ,  30 ,  79 ],
               [  64 ,  92 ,  39 ,  49 ,  33 ,  46 ,  30 ,  0  ,  92 ],
               [  44 ,  102,  94 ,  50 ,  70 ,  83 ,  79 ,  92 ,  0  ]]

def formClusters(dists, distance):
    # Make distance matrix square
    dists = ssd.squareform(dists)
    # Compute linkage
    links = sch.linkage(dists)

    plt.figure(figsize=(15,5))
    p = sch.dendrogram(links)
    plt.show()

formClusters(distMatrix, 38)
