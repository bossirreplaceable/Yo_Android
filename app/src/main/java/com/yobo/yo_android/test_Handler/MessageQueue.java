package com.yobo.yo_android.test_Handler;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
class MessageQueue {

    private Message mMessages;

    MessageQueue(boolean quitAllowed) {
    }

    /**
     * 将Message 对象插入到单链表中
     */
    boolean enqueueMessage(Message msg, long when) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }

        synchronized (this) {

            msg.markInUse();
            msg.when = when;
            Message p = mMessages;

            /*
             * 1、下面的操作是为了将单链表中的所有Message按照 延迟发送的时间排序
             * 2、新添加的msg需要插入到单链表中合适的位置
             */
            if (p == null || when == 0 || when < p.when) {
                // 将这个msg放在单链表的头部
                msg.next = p;
                mMessages = msg;
            } else {
                Message prev;
                for (;;) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p; // invariant: p == prev.next
                prev.next = msg;
            }

        }
        return true;
    }

    Message next() {

        for (;;) {
            synchronized (this) {
                Message prevMsg = null;
                Message msg = mMessages;
                /*
                 * 如果Message 没有target就抛弃这些Message
                 */
                if (msg != null && msg.target == null) {
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null);
                }

                if (msg != null) {

                    // Got a message.
                    if (prevMsg != null) {
                        prevMsg.next = msg.next;
                    } else {
                        mMessages = msg.next;
                    }
                    msg.next = null;
                    msg.markInUse();
                    return msg;
                }

            }
        }
    }

}
