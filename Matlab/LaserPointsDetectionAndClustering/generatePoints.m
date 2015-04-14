% function generatePoints(centroids,sigma)

figure
for i= 1:3
    r = mvnrnd(centroids(i,:),sigma,20);
    plot(r(:,1),r(:,2),'.');
    hold on;
end

hold off;