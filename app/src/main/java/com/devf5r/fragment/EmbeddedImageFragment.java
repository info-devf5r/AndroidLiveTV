package com.devf5r.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.devf5r.yochannel.EmbeddedPlayerActivity;
import com.devf5r.yochannel.R;
import com.squareup.picasso.Picasso;

public class EmbeddedImageFragment extends Fragment {


    public static EmbeddedImageFragment newInstance(String streamUrl, String imageCover, boolean isPlayVisible) {
        EmbeddedImageFragment f = new EmbeddedImageFragment();
        Bundle args = new Bundle();
        args.putString("streamUrl", streamUrl);
        args.putString("imageCover", imageCover);
        args.putBoolean("isPlayVisible", isPlayVisible);
        f.setArguments(args);
        return f;
    }

    ImageView imageCover, imagePlay;
    String streamUrl, imageUrl;
    boolean isPlayVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_embedded_image, container, false);

        if (getArguments() != null) {
            streamUrl = getArguments().getString("streamUrl");
            imageUrl = getArguments().getString("imageCover");
            isPlayVisible = getArguments().getBoolean("isPlayVisible");
        }

        imageCover = rootView.findViewById(R.id.imageCover);
        imagePlay = rootView.findViewById(R.id.imagePlay);

        if (isPlayVisible) {
            imagePlay.setVisibility(View.VISIBLE);
        } else {
            imagePlay.setVisibility(View.GONE);
        }

        Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder_slider).into(imageCover);

        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!streamUrl.isEmpty()) {
                    Intent intent = new Intent(getActivity(), EmbeddedPlayerActivity.class);
                    intent.putExtra("streamUrl", streamUrl);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.stream_not_found), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
