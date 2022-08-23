package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {

        var list = new ArrayList<Integer>();
        for (ListNode linkedList : lists) {

            while (linkedList != null) {
                list.add(linkedList.val);
                linkedList = linkedList.next;
            }

        }

        if (list.size() == 0) {
            return null;
        }

        Collections.sort(list);
        var ans = new ListNode();
        var head = ans;
        for (int i = 0; i < list.size(); i++) {
            ans.val = list.get(i);
            if (i < list.size() - 1) {
                ans.next = new ListNode();
                ans = ans.next;
            }
        }

        return head;

    }

    @Test
    public void test() {
        mergeKLists(new ListNode[]{});
    }
}
