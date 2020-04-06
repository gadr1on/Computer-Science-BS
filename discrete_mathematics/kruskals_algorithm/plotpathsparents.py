#!&lt;path to your python&gt;/bin/python2.7
'''
Plots the points of a given set and the paths between them
that gives the minimum spanning tree among them
'''
import os
from pylab import matplotlib, plt, sqrt

def FindFirstandLastCost(xpoints, ypoints, parentlines, nParents):
  ''' This can be used in the visualization of the cost function to
      find the max and min extents
  '''
  str_parents = parentlines[1].split() # 0th is dummy
  parents = [int(i) for i in str_parents]

  cost_first = 0
  for i in range(1, nParents):
    cost_first += sqrt((xpoints[i]-xpoints[parents[i]])**2  + (ypoints[i]-ypoints[parents[i]])**2)

  str_parents = parentlines[-1].split()
  parents = [int(i) for i in str_parents]

  cost_last = 0
  for i in range(1, nParents):
    cost_last += sqrt((xpoints[i]-xpoints[parents[i]])**2  + (ypoints[i]-ypoints[parents[i]])**2)

  return (cost_first, cost_last)

def PlotPointsOnly(ax, xpoints, ypoints, nPoints, k=0):
  '''
    Here just the plotting of the points as small circles done.
    The connections between them (i.e. paths) are done elsewhere
  '''

  for i in range(nPoints):
    if i == 0:
      plt.plot(xpoints[i], ypoints[i], 'mo', markersize=10)
      #ax.text(x[i]+2,y[i],'Origin',color='r',fontsize=14)
      #ax.text(x[i]+2,y[i],r'$P_{Origin}$',color='r',fontsize=15)
      #plt.figtext(0.1,0.1,'Origin',color='r',fontsize=10)
    else:
      plt.plot(xpoints[i], ypoints[i], 'mo', markersize=10)

  plt.axis('image')
  plt.grid('on')
  plt.xlabel('x')
  plt.ylabel('y')
  plt.title('Iteration # '+str(k)+' of '+str(nPoints), fontsize=16)

  if ARGS.verbose:
    print "min(x) = ", min(xpoints), " max(x)=", max(xpoints)
    print "min(y) = ", min(ypoints), " max(y)=", max(ypoints)
  axes = plt.gca()
  #axes.set_xlim( -200, 215 )
  axes.set_xlim(min(xpoints)-5, max(xpoints)+5)
  axes.set_ylim(min(ypoints)-5, max(ypoints)+5)

  return

def GetPointsAndPaths():
  '''
  Read the input files and get necessary data for plotting
  '''

  # Read the input files for processing
  with open(ARGS.input) as f:
    lines = f.read().splitlines()

  nPoints = int(lines[0])

  if ARGS.verbose:
    print "lines=", lines
    print "nPoints = ", nPoints

  x, y = [[], []]
  for i in range(nPoints):
    temp = (lines[i+1]).split()
    #print "temp= ", temp
    x.append(float(temp[0]))
    y.append(float(temp[1]))

  print "x= ", x, " min(x) = ", min(x), " max(x) = ", max(x)
  print "y= ", y, " min(y) = ", min(y), " max(y) = ", max(y)

  with open(ARGS.connectedpoints) as f:
    connectedpointslines = f.read().splitlines()

  print "connectedpointslines  = ", connectedpointslines

  nConnectedPoints = len(connectedpointslines)
  print "nConnectedPoints = ", nConnectedPoints

  return (x, y, connectedpointslines, nPoints, nConnectedPoints)

# --------------------------------------------------
def main():

  x, y, connectedpointslines, nPoints, nConnectedPoints = GetPointsAndPaths()

  # For interactive plotting in python, check: http://stackoverflow.com/questions/11874767/real-time-plotting-in-while-loop-with-matplotlib
  plt.ion()

  # - Plot just the points (no connecting paths)
  fig2 = plt.figure(5, figsize=(16, 9)) ## This mean 16x9 inches (number of pixels is (16x9)*dpi value set in savefig
  fig2.patch.set_facecolor('white')

  ax = plt.subplot2grid((1, 3), (0, 0), colspan=2)

  PlotPointsOnly(ax, x, y, nPoints)

  plt.savefig('test_dpi240_16x9__initial.png', facecolor='w', dpi=240)

  #Find the first and last costs to set the y-limits of cost-vs-iteration plots
  #cost_first, cost_last= FindFirstandLastCost(x, y, connectedpointslines,nConnectedPoints)
  #if ARGS.verbose: print "cost_first, cost_last= ",cost_first," ", cost_last

  print "Start iterations:"

  cost_arr = []
  for k in range(0, nConnectedPoints):
  #for k in range(1):
  #for k in range(nConnectedPoints-1,nConnectedPoints):
    print "k= ", k, " of ", nConnectedPoints
    str_connectedpoints = connectedpointslines[k].split()
    #print "str_connectedpoints  = ", str_connectedpoints
    connectedpoints = [int(i) for i in str_connectedpoints]
    #print "connectedpoints= ", connectedpoints

    plt.cla()
    plt.clf()

    #ax = plt.subplot(1,2,1)
    ax = plt.subplot2grid((1, 3), (0, 0), colspan=2)

    PlotPointsOnly(ax, x, y, nPoints, k)

    # plot the paths
    cost_k = 0
    for i in range(0, k+1):

      str_connectedpoints = connectedpointslines[i].split()
      connectedpoints = [int(j) for j in str_connectedpoints]
      m, n = connectedpoints

      plt.plot([x[m], x[n]], [y[m], y[n]], '-k', linewidth=2)
      cost_k += sqrt((x[m]-x[n])**2  + (y[m]-y[n])**2)

    cost_arr.append(cost_k)
    #print "cost_arr =", cost_arr
    print "cost_k =", cost_k

    #PlotPointsOnly(ax,x,y,nPoints,k)

    #plt.pause(0.5)

    # Plot the cost vs iterations
    ax = plt.subplot2grid((1, 3), (0, 2), colspan=1)
    plt.plot(cost_arr, '-mo', markersize=6)
    axes = plt.gca()
    axes.set_xlim(-1, k+2)
    axes.set_ylim(0, 700)

    plt.grid('on')
    plt.title(r'Cost: $\sum_{\forall\, i,j} (P_{i}-P_{j})_{connected}$ = ' +str(int(cost_k*10)/10.0), fontsize=15, y=1.02)
    plt.xlabel('Number of iterations', fontsize=14)
    plt.ylabel('Cost: Sum of all connected distances', fontsize=14)

    # Dump png file for later video processing
    plt.savefig('test_dpi240_16x9_'+str(k)+'.png', facecolor='w', dpi=240)
    plt.pause(0.1)

  # This is for interactive plotting plt.ion()
  while True:
    plt.pause(0.05)

# -- Parse the input ---------------------------------------------------------
def ParseInput():
  '''
  Read input arguments to be plotted by this script
  '''

  import argparse
  parser = argparse.ArgumentParser()
  parser.add_argument("-v", "--verbose", help="Increase output verbosity", action="store_true")
  parser.add_argument("-input", type=str, default=None, help="enter original input")
  parser.add_argument("-connectedpoints", type=str, default=None, help="Enter cost and distance file")

  args = parser.parse_args()

  if not args.input or not args.connectedpoints:
    print "Enter cost and input files"
    exit(1)

  if args.verbose:
    print "input=", args.input
    print "connectedpoints=", args.connectedpoints

  return args
# -----------------------------------------------------------------------------

# This is the standard boilerplate that calls the main() function.
if __name__ == '__main__':

  ARGS = ParseInput()

  main()
