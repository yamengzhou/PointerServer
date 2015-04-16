close all, clear all;
% centroids = [[1,1];[4,4];[7,3]];
% sigma = [[0.3,0.2];[0.2,0.3]];
% 
% generatePoints(centroids,sigma);

Cluster_Num = 3;
%% Simple image processing
input = imread('test_2.png');
dots = double(input);
hsv_dots = rgb2hsv(dots);

test_img = hsv_dots(:,:,3);
test_img = 255 - test_img;

backup_img = test_img;

%% labeling dots on the image
[processed_img dots_num]= imgTagging(backup_img);

%% find the centroids of those dots
centroids = findCentroids(processed_img, dots_num);

%% clustering centroids by using kmeans
% Problems remain like if we do not know the initial number of clusters,
% how could we do the clustering using techniques like k-means?
model = kmeans(centroids,Cluster_Num);

%% plot the clustering result
colors = ['g*';'ro';'b+'];
figure
hold on
for i = 1:Cluster_Num
    for j = 1:dots_num
       if model(j) == i
           plot(centroids(j,1),centroids(j,2),colors(i,:));
       end
    end
end
hold off

%% Put boxes and count number on each cluster
figure;
hold on;
[r,c] = size(test_img);
output = input;
rectangle = zeros(0,4);
for i = 1:Cluster_Num
    dots_set = zeros(0,2);
   for j = 1:dots_num
      if  model(j) == i
          dots_set = [dots_set;centroids(j,:)];
      end
   end
   u_y = max(dots_set(:,1));
   u_x = max(dots_set(:,2));
   l_y = min(dots_set(:,1));
   l_x = min(dots_set(:,2));
   % Insert boxes
   shapeInserter = vision.ShapeInserter;
   rectangle = int32([l_x-15 l_y-15 u_x-l_x+30 u_y-l_y+30]);
   output = step(shapeInserter,output,rectangle);
   
   % Insert text
   position = [l_x-15,l_y-30];
   [value cols] = size(dots_set);
   output = insertText(output,position,value,'AnchorPoint','LeftBottom');
   
   imshow(output);
end

imshow(output);
hold off;












