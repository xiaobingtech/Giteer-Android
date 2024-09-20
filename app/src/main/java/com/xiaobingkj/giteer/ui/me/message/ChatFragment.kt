package com.xiaobingkj.giteer.ui.me.message

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.xiaobingkj.giteer.data.model.Message
import com.xiaobingkj.giteer.data.model.MessageBean
import com.xiaobingkj.giteer.data.model.User
import com.xiaobingkj.giteer.data.storage.Storage
import com.xiaobingkj.giteer.ui.MainActivity
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
                sendMsg()
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

    private fun sendMsg() {
        val intent = Intent(mActivity, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(mActivity, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(mActivity, "910529")
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("通知标题")
            .setContentText("通知内容")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(mActivity)) {
            if (ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                //                                        grantResults: IntArray)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                return@with
            }
            // notificationId is a unique int for each notification that you must define.
            notify(Date().time.toInt(), builder.build())
        }
    }

    override fun lazyLoadData() {

    }

    override fun showLoading(message: String) {

    }

}