# sMarket
sMarket open Project
…or create a new repository on the command line


echo "# sMarket" >> README.md</br>
git init</br>
git add README.md</br>
git commit -m "first commit"</br>
git remote add origin git@github.com:JeffreyLau/sMarket.git</br>
git push -u origin master</br>
…or push an existing repository from the command line</br>


git remote add origin git@github.com:JeffreyLau/sMarket.git</br>
git push -u origin master</br>
…or import code from another repository</br>

You can initialize this repository with code from a Subversion, Mercurial, or TFS project.</br>

Import code</br>

#creat sPlay branch</br>
git branch sPlay</br>
git push origin sPlay</br>
#sync local and remote branch</br>
git fetch sPlay</br>
git checkout --track origin/sPlay</br>

git push origin <local_branch_name>:<remote_branch_name></br>
git push origin sPlay:sPlay</br>
#当然如果当前在sPlay分支下，也可以直接</br>
git push</br>
#删除远程分支sPlay:</br>
git push origin :sPlay</br>

git push origin :sPlay</br>
To git@github.com:JeffreyLau/sMarket.git</br>
 - [deleted]         sPlay</br>

