import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
        advance();
    }

    private void advance() {
        nextEl = null;
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else {
                NestedInteger tmp = st.peek().next();
                if (tmp.isInteger()) {
                    nextEl = tmp;
                    break;
                } else {
                    st.push(tmp.getList().iterator());
                }
            }
        }
    }

    @Override
    public Integer next() {
        Integer temp = nextEl.getInteger();
        advance();
        return temp;

    }

    @Override
    public boolean hasNext() {
        return nextEl != null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//TC: O(1) Amortized - worst case O(N)
//SC: O(D) depth of nesting
