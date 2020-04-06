import numpy as np
import pandas as pd
import seaborn as sns

df_train_features = pd.read_csv('data/dengue_features_train.csv', index_col= [0, 1, 2])
df_train_labels = pd.read_csv('data/dengue_labels_train.csv', index_col= [0, 1, 2])
df_test_features = pd.read_csv('data/dengue_features_test.csv', index_col= [0, 1, 2])

print(df_train_features.head())
print(df_train_features.shape)
