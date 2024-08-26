package com.xiaobingkj.giteer.ui.me.message

import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.xiaobingkj.giteer.data.model.Message
import com.xiaobingkj.giteer.data.model.MessageBean
import com.xiaobingkj.giteer.data.model.User
import com.xiaobingkj.giteer.data.storage.Storage
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.FragmentChatBinding
import me.hgj.jetpackmvvm.base.fragment.BaseVmDbFragment
import java.util.Date
import java.util.UUID


class ChatFragment() : BaseVmDbFragment<ChatViewModel, FragmentChatBinding>() {
    override fun layoutId(): Int = R.layout.fragment_chat
    private var adapter: MessagesListAdapter<Message>? = null
    private var imageLoader: ImageLoader? = null
    private var messages: Array<Message> = emptyArray()
    override fun createObserver() {
        mViewModel.msgEvent.observe(viewLifecycleOwner) {
            val user = Storage.user
            val message = Message(UUID.randomUUID().leastSignificantBits.toString(), User(user.id.toString(), user.name, user.avatar_url, false), it.content, Date())
            adapter?.addToStart(message, true)
            mViewModel.push(messages.first().user.id, it.content, user.login)
        }
        mViewModel.pushEvent.observe(viewLifecycleOwner) {
            Log.d("PushEvent", it)
        }
    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val msgs = arguments?.getParcelableArray("msgs") as Array<MessageBean.ListDTO>

        mActivity.supportActionBar?.title = msgs.first().sender.name

        val temp:List<Message>  = msgs.map {
            Message(it.id.toString(), User(it.sender.id.toString(), it.sender.name, it.sender.avatar_url, false), it.content, TimeUtils.string2Date(it.updated_at, "yyyy-MM-dd'T'HH:mm:ssXXX"))
        }
        messages = temp.toTypedArray()

        imageLoader = ImageLoader { imageView, url, payload ->
            Glide.with(requireView())
                .load(url)
                .into(imageView)
        }

        adapter = MessagesListAdapter(messages.first().id, imageLoader)
        adapter?.addToEnd(messages.toList(), false)

        mDatabind.messagesList.setAdapter(adapter)

        mDatabind.input.setInputListener(object: MessageInput.InputListener {
            override fun onSubmit(input: CharSequence?): Boolean {
                //发送信息
                mViewModel.sendMsg(msgs.first().sender.login, input.toString())
                return true
            }
        })
        mDatabind.input.setTypingListener(object: MessageInput.TypingListener {
            override fun onStartTyping() {

            }

            override fun onStopTyping() {

            }

        })
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}