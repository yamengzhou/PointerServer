close all, clear all;
% centroids = [[1,1];[4,4];[7,3]];
% sigma = [[0.3,0.2];[0.2,0.3]];
% 
% generatePoints(centroids,sigma);

latt2 = imread('latt2.png');

dots = double(latt2);
hsv_dots = rgb2hsv(dots);

test_img = hsv_dots(:,:,3);
test_img = 255 - test_img;

backup_img = test_img;

[processed_img dots_num]= imgTagging(backup_img);