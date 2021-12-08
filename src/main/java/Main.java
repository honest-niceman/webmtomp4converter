import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.encode.enums.X264_PROFILE;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoSize;
import ws.schild.jave.progress.EncoderProgressListener;

import java.io.File;

public class Main {


    public static void main(String[] args) {
        ConvertProgressListener listener = new ConvertProgressListener();

        boolean succeeded = true;
        File source = new File("gridDynamics.webm");
        File target = new File("target.mp4");

        /* Step 2. Set Audio Attrributes for conversion*/
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        // here 64kbit/s is 64000
        audio.setBitRate(64000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        /* Step 3. Set Video Attributes for conversion*/
        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setX264Profile(X264_PROFILE.BASELINE);
        // Here 160 kbps video is 160000
        video.setBitRate(160000);
        // More the frames more quality and size, but keep it low based on devices like mobile
        video.setFrameRate(15);
        video.setSize(new VideoSize(1280, 720));

        /* Step 4. Set Encoding Attributes*/
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setInputFormat("webm");
        attrs.setOutputFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);

        /* Step 5. Do the Encoding*/
        try {
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
        } catch (Exception e) {
            /*Handle here the video failure*/
            e.printStackTrace();
        }
        System.out.println(succeeded);
    }
}

class ConvertProgressListener implements EncoderProgressListener {

    public ConvertProgressListener() {
        //code
    }

    public void message(String m) {
        //code
    }

    public void progress(int p) {

        //Find %100 progress
        double progress = p / 1000.00;
        System.out.println(progress);
    }

    public void sourceInfo(MultimediaInfo m) {
        //code
    }
}
