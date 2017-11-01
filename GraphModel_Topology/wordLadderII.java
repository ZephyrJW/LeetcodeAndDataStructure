/* two-end BFS */
class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> fwdQueue = new HashSet<>(), bckQueue = new HashSet<>();
            Map<String, List<String>> h = new HashMap<>();
            fwdQueue.add(beginWord);
            bckQueue.add(endWord); 
            List<List<String>> res = new ArrayList<>();
            Set<String> set = new HashSet<>(wordList);//ken remove duplicates!!
            if (set.contains(endWord) == false) {
                return res;
            }
            findLadder(fwdQueue, bckQueue, set, true, h);

            List<String> cur = new ArrayList<>();
            cur.add(beginWord);
            printPath(beginWord, endWord, cur, h, res);
            return res;
        }

        private void findLadder(Set<String> fwdQueue, Set<String> bckQueue, Set<String> wordList, boolean direction, Map<String, List<String>> h) {
            boolean found = false;
            if (fwdQueue.size() == 0 || fwdQueue.size() == 0) {
                return;
            }
            if (fwdQueue.size() > bckQueue.size()) {
                findLadder(bckQueue, fwdQueue, wordList, !direction, h);
                return;
            }
            wordList.removeAll(fwdQueue);
            wordList.removeAll(bckQueue);
            Set<String> setNew = new HashSet<>();

            for (String s : fwdQueue) {
                char[] chs = s.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    char tmpc = chs[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (j != tmpc) {
                            chs[i] = j;
                            String tmp = new String(chs);

                            if (bckQueue.contains(tmp)) {
                                found = true;
                                addPath(s, tmp, direction, h);
                            } else {
                                if (!found && wordList.contains(tmp)) {
                                    setNew.add(tmp);
                                    addPath(s, tmp, direction, h);
                                }
                            }
                        }
                    }
                    chs[i] = tmpc;
                }
            }
            if (!found) {
                findLadder(setNew, bckQueue, wordList, direction, h);
            }
        }
        private void addPath(String s, String t, boolean dir, Map<String, List<String>> h) {
            String key = dir ? s : t, val = dir ? t : s;
            List l = h.getOrDefault(key, new ArrayList<>());
            l.add(val);
            h.put(key, l);
        }

        private void printPath(String s, String target, List<String> cur, Map<String, List<String>> h, List<List<String>> ans) {
            if (s.equals(target)) {
                ans.add(new ArrayList<>(cur));
                return;
            }
            if (!h.containsKey(s)) {
                return;
            }
            for (String i : h.get(s)) {
                cur.add(i);
                printPath(i, target, cur, h, ans);
                cur.remove(cur.size() - 1);
            }
        }
}


/*BFS build graph, then DFS build path*/
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        dict.add(beginWord);
        
        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, List<String>> nodeNeighbors = new HashMap<>();
        dist.put(beginWord, 0);
        
        for(String s: dict) nodeNeighbors.put(s, new LinkedList<String>());
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        while(!queue.isEmpty()){
            int size=queue.size();
            boolean found = false;
            for(int i=0; i< size; i++){
                String cur = queue.poll();
                List<String> neighbors = findNeighbors(cur, dict);
                
                for(String neighbor: neighbors){
                    nodeNeighbors.get(cur).add(neighbor);
                    if(!dist.containsKey(neighbor)){
                        dist.put(neighbor, dist.get(cur)+1);
                        if(neighbor.equals(endWord)) found = true;
                        queue.offer(neighbor);
                    }
                }
            }
            if(found) break;
        }
        
        List<String> ans = new LinkedList<>();
        List<List<String>> ret = new LinkedList<>();
        ans.add(beginWord);
        helper(ret, ans, endWord,nodeNeighbors, dist);
        return ret;
    }
    
    private void helper(List<List<String>> ret, List<String> ans, String e, HashMap<String, List<String>> nodeNeighbors, HashMap<String, Integer> dist){
        if(ans.get(ans.size()-1).equals(e)) ret.add(new LinkedList<String>(ans));
        else{
            String cur = ans.get(ans.size()-1);
            for(String s: nodeNeighbors.get(cur)){
                if(dist.get(s)==dist.get(cur)+1){
                    ans.add(s);
                    helper(ret, ans, e, nodeNeighbors, dist);
                    ans.remove(ans.size()-1);
                }
            }
        }
    }
    
    private List<String> findNeighbors(String s, HashSet<String> dict){
        List<String> list = new LinkedList<>();
        char[] chars = s.toCharArray();
        for(int i=0; i< chars.length; i++){
            for(char ch='a'; ch<='z'; ch++){
                if(chars[i]==ch) continue;
                char tmp = chars[i];
                chars[i] = ch;
                if(dict.contains(String.valueOf(chars))) list.add(String.valueOf(chars));
                chars[i] = tmp;
            }
        }
        return list;
    }
}
