%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Author: Yameng Zhou
% Date: April 15
% Description: This is a matlab code for dots clustering waiting for
% further optimization and modification.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


function centroids = findCentroids(processed_img,dots_num)
% dots_num = 25;
centroids = zeros(0,2);
[r,c] = size(processed_img);

for n = 1:dots_num
    center = zeros(1,2);
    count = 0;
    for i = 1:r
        for j = 1:c
            if processed_img(i,j) == n
                center = center + [i,j];
                count = count + 1;
            end
        end
    end
    center = center./count;
    centroids = [centroids; center];
end

centroids = floor(centroids);