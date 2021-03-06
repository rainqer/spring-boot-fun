package info.rnowak.springFun.web;

import info.rnowak.springFun.domain.Message;
import info.rnowak.springFun.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> list(@PathVariable int page) {
        return messageRepository.findAll(new PageRequest(page, DEFAULT_PAGE_SIZE)).getContent();
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Message find(@PathVariable Long id) {
        return messageRepository.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Message create(@RequestBody Message message) {
        return messageRepository.save(message);
    }
}
