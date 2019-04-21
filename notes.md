Way to full replace master code from branch code:
-------------------------------------------------
git checkout staging
git merge --strategy=ours master
git checkout master
git merge staging