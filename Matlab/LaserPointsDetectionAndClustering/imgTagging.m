function [processed_img num_dots]= imgTagging(img)
% img = backup_img;
[r,c] = size(img);

processed_img = zeros(r,c);

count = 1;
for i = 1:r
    for j = 1:c
        if img(i,j) > 200
            processed_img(i,j) = count;
            count = count + 1;
        end
    end
end

fprintf('first round neighour processing\n')
for i = 1:r
    for j = 1:c
        processed_img(i,j) = neighbourProcess(processed_img,i,j,r,c);
    end
end

fprintf('second round neighour processing in backward scanning\n')
for i = r:-1:1
    for j = c:-1:1
        processed_img(i,j) = neighbourProcess(processed_img,i,j,r,c);
    end
end


%% To relabel the pixel based on the total number
count = 1;
m = containers.Map('KeyType','double','ValueType','int32');

for i = 1:r
    for j = 1:c
        if processed_img(i,j) ~= 0 && m.isKey(processed_img(i,j)) == 0
            m(processed_img(i,j)) = 1;
        end
    end
end

keys = m.keys;
keys_mat = cell2mat(keys);
[k_r k_c] = size(keys_mat);
num_dots = k_c;
replacement = containers.Map('KeyType','double','ValueType','double');
for i = 1:k_c
   replacement(keys_mat(i)) = count;
   count = count + 1;
end

for i = 1:r
    for j = 1:c
        if processed_img(i,j) ~= 0 && replacement.isKey(processed_img(i,j))
           processed_img(i,j) = replacement(processed_img(i,j)); 
        end
    end
end

